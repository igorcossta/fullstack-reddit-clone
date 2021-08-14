import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

import { SubredditProps } from '../../@types/subreddit.type';
import { RedditAPI } from '../../axios/reddit.api';
import { SubredditCard, Toastr } from '../../component';
import { useAuth } from '../../context/account';
import { Container, Wrapper } from './styles';

const Dashboard: React.FC = () => {
  const [subreddit, setSubreddit] = useState<SubredditProps[]>([]);
  const { user } = useAuth();

  useEffect(() => {
    RedditAPI.get(`/api/subreddit/user/${user?.username}`)
      .then(({ data }) => setSubreddit(data))
      .catch(() => Toastr.info('Maybe your data has not been loaded.'));
  }, [user]);

  return (
    <Wrapper>
      <Container>
        <div className="subreddit">
          {subreddit.length !== 0 ? (
            subreddit.map((x) => (
              <SubredditCard
                key={x.subredditId}
                name={x.name}
                description={x.description}
                numberOfPosts={x.numberOfPosts}
              />
            ))
          ) : (
            <>
              <h3>You don&apos;t have any subreddit.</h3>
            </>
          )}
        </div>
        <div className="tools">
          <div className="user__info">
            <div className="user__photo">
              <img src="https://avatars.githubusercontent.com/u/65612587?v=4" alt="user" />
            </div>
            <h1>
              {user?.firstName} {user?.lastName}
            </h1>
            <h2>{user?.username}</h2>
          </div>

          <div>
            <Link className="user__crSubreddit" to="/account/create-subreddit">
              Create new Subreddit
            </Link>
          </div>
        </div>
      </Container>
    </Wrapper>
  );
};

export default Dashboard;

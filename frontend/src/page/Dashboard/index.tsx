import React, { Fragment, useEffect, useState } from 'react';

import { SubredditProps } from '../../@types/subreddit.type';
import userPhoto from '../../assets/svg/female_avatar.svg';
import { axios as RedditAPI } from '../../axios/axios.config';
import { Button, SubredditCard, Toastr } from '../../component';
import CreateSubredditForm from '../../component/CreateSubredditForm';
import { useAuth } from '../../context/account';
import { Container, Banner, Subreddit } from './styles';

const Dashboard: React.FC = () => {
  const [subreddit, setSubreddit] = useState<SubredditProps[]>([]);
  const { user } = useAuth();

  useEffect(() => {
    if (user?.username) {
      RedditAPI.get(`/api/subreddit/by-user/${user.username}`)
        .then(({ data }) => setSubreddit(data))
        .catch(() => Toastr.info('Maybe your data has not been loaded.'));
    }
  }, [user]);

  return (
    <Container>
      <Banner>
        <div>
          <img src={userPhoto} alt="user" />
          <h3>
            {user?.firstName} {user?.lastName}
          </h3>
          <h5>{user?.username}</h5>
        </div>
        <div>
          <CreateSubredditForm />
        </div>
      </Banner>
      <Subreddit>
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
      </Subreddit>
    </Container>
  );
};

export default Dashboard;

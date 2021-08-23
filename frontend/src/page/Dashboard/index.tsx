import React, { useEffect, useState } from 'react';
import Modal from 'react-modal';

import { SubredditProps } from '../../@types/subreddit.type';
import userPhoto from '../../assets/svg/female_avatar.svg';
import { axios as RedditAPI } from '../../axios/axios.config';
import { Button, SubredditCard, Toastr } from '../../component';
import CreateSubredditForm from '../../component/CreateSubredditForm';
import { useAuth } from '../../context/account';
import { useToggle } from '../../hook';
import { Container, Banner, Subreddit } from './styles';

const style = {
  content: {
    top: '50%',
    left: '50%',
    right: 'auto',
    bottom: 'auto',
    marginRight: '-50%',
    transform: 'translate(-50%, -50%)',
  },
};

const Dashboard: React.FC = () => {
  const [isOpen, toggle] = useToggle(false);
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
      <Modal isOpen={isOpen} style={style}>
        <CreateSubredditForm close={toggle} />
      </Modal>
      <Banner>
        <div>
          <img src={userPhoto} alt="user" />
          <h3>
            {user?.firstName} {user?.lastName}
          </h3>
          <h5>{user?.username}</h5>
        </div>
        <Button type="button" onClick={toggle}>
          Create new Subreddit
        </Button>
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

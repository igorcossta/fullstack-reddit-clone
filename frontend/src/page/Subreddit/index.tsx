import React, { useCallback, useEffect, useState } from 'react';
import Modal from 'react-modal';
import { useParams } from 'react-router-dom';

import { PostProps, SubredditProps } from '../../@types/subreddit.type';
import { RedditAPI } from '../../axios/reddit.api';
import { Button, Card, CreatePostForm } from '../../component';
import { useAuth } from '../../context/account';
import { Container, Banner, Posts } from './styles';

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

const Subreddit: React.FC = () => {
  const [isOpen, setIsOpen] = useState(false);
  const [posts, setPosts] = useState<PostProps[]>([]);
  const [subreddit, setSubreddit] = useState<SubredditProps>();
  const { signed } = useAuth();
  const { subredditName } = useParams<{ subredditName: string }>();

  const toggle = useCallback(() => {
    setIsOpen(!isOpen);
  }, [isOpen]);

  const fetchPosts = useCallback((id: number | undefined) => {
    RedditAPI.get(`/api/post/by-subreddit/${id}`)
      .then(({ data }) => {
        setPosts(data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  useEffect(() => {
    RedditAPI.get(`/api/subreddit/by-name/${subredditName}`)
      .then(({ data }) => {
        setSubreddit(data);
        fetchPosts(data.subredditId);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [subredditName, fetchPosts]);

  return (
    <Container>
      <Modal isOpen={isOpen} style={style}>
        <CreatePostForm close={toggle} />
      </Modal>
      <Banner>
        <div>
          <h3>{subreddit?.name}</h3>
          <h5>{subreddit?.description}</h5>
        </div>
        {signed && (
          <Button type="button" onClick={toggle}>
            Create new Post
          </Button>
        )}
      </Banner>
      <Posts>
        {posts.length !== 0 ? (
          posts.map((x) => (
            <Card
              key={x.id}
              subreddit={x.subredditName}
              username={x.username}
              time={x.duration}
              comment={x.commentCount}
              vote={x.voteCount}
              content={x.description}
            />
          ))
        ) : (
          <>
            <h3>{subreddit?.name} don&apos;t have any post.</h3>
          </>
        )}
      </Posts>
    </Container>
  );
};

export default Subreddit;

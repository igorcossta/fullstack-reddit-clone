import React, { useCallback, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

import { PostProps } from '../../@types/post.type';
import { SubredditProps } from '../../@types/subreddit.type';
import { axios as RedditAPI } from '../../axios/axios.config';
import { Card, CreatePostForm } from '../../component';
import { useAuth } from '../../context/account';
import { Container, Banner, Posts } from './styles';

const Subreddit: React.FC = () => {
  const [posts, setPosts] = useState<PostProps[]>([]);
  const [subreddit, setSubreddit] = useState<SubredditProps>();
  const { signed } = useAuth();
  const { subredditName } = useParams<{ subredditName: string }>();

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
      <Banner>
        <div>
          <h3>{subreddit?.name}</h3>
          <h5>{subreddit?.description}</h5>
        </div>
        {signed && (
          <div>
            <CreatePostForm />
          </div>
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

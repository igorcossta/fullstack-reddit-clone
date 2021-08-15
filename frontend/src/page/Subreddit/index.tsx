import React, { useCallback, useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';

import { PostProps, SubredditProps } from '../../@types/subreddit.type';
import { RedditAPI } from '../../axios/reddit.api';
import { Card } from '../../component';
import { Container, Wrapper } from './styles';

const Subreddit: React.FC = () => {
  const { subredditName } = useParams<{ subredditName: string }>();
  const [subreddit, setSubreddit] = useState<SubredditProps>();
  const [posts, setPosts] = useState<PostProps[]>([]);

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
    <Wrapper>
      <Container>
        <div className="subreddit__bg">
          <h1>{subreddit?.name}</h1>
          <p>{subreddit?.description}</p>
        </div>

        <div className="subreddit__content">
          <div className="subreddit__posts">
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
          </div>
          <div className="subreddit__tools">
            <Link to={`/r/${subredditName}/create-post`}>Create new Post</Link>
          </div>
        </div>
      </Container>
    </Wrapper>
  );
};

export default Subreddit;

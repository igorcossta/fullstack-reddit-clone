import React, { useState, useEffect } from 'react';

import { PostProps } from '../../@types/subreddit.type';
import { RedditAPI } from '../../axios/reddit.api';
import { Card } from '../../component';
import { Container, Content, Side } from './styles';

const Home: React.FC = () => {
  const [posts, setPosts] = useState<PostProps[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);

  useEffect(() => {
    async function fetch() {
      await RedditAPI.get('/api/subreddit')
        .then(({ data }) => {
          setPosts(data);
        })
        .catch(() => setError(true))
        .finally(() => setLoading(false));
    }
    fetch();
  }, []);

  if (error) {
    return <h6>ocorreu um erro. Tente novamente!</h6>;
  }

  return (
    <Container>
      <Side>
        <h1>side</h1>
      </Side>
      <Content>
        {loading ? (
          <h6>carregando</h6>
        ) : (
          posts.map((x) => (
            <Card
              key={x.subreddit}
              subreddit={x.subreddit}
              username={x.username}
              time={x.time}
              comment={x.comment}
              vote={x.vote}
              content={x.content}
            />
          ))
        )}
      </Content>
    </Container>
  );
};

export default Home;

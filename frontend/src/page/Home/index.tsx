import React, { useState, useEffect } from 'react';

import { Card } from '../../component';
import { browser } from '../../service/server';
import { Container, Content, Side } from './styles';

const Home: React.FC = () => {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);

  useEffect(() => {
    async function fetch() {
      await browser
        .get('/api/subreddit')
        .then((res) => {
          setPosts(res.data);
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
          <Card
            key="1"
            subreddit="reddit"
            username="master"
            time="just now"
            comment={10000}
            vote={20000}
            content="lorem ipsum dolor sit amet."
          />
        )}
      </Content>
    </Container>
  );
};

export default Home;

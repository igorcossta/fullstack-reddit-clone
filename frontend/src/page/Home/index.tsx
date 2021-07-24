import React, { useEffect, useState } from 'react';

import Card from '../../component/Card';
import api from '../../service/http';
import { PostResponse } from '../../service/type';
import { Container, Content, Side } from './styles';

const Home: React.FC = () => {
  const [data, setData] = useState<PostResponse[]>([]);
  const [error, setError] = useState(false);

  // send request to API to get most popular posts
  useEffect(() => {
    api
      .get('/post?vote_gte=2000')
      .then((res) => {
        setError(false);
        setData(res.data);
      })
      .catch((err) => {
        setError(true);
      });
  }, []);

  return (
    <Container>
      <Content>
        {(function hasError() {
          if (error) {
            return (
              <span>
                Não foi possível carregar as publicações. <a href="/">Tentar novamente</a>
              </span>
            );
          }
          if (data.length === 0) {
            return <span>carregando...</span>;
          }
          return data.map((i) => (
            <Card
              subreddit={i.subreddit}
              username={i.user}
              comment={i.comment}
              vote={i.vote}
              time={i.createdAt}
              content={i.content}
              key={i.id}
            />
          ));
        })()}
      </Content>
      <Side />
    </Container>
  );
};

export default Home;

import { Body, Container, Content, MT, Side } from './style';
import React, { useEffect, useState } from 'react';

import { ApiTeste } from '../../config/type';
import Card from '../../component/Card';
import Header from '../../component/Header';
import { post } from '../../config/http';

const Main: React.FC = () => {
  const [data, setData] = useState<ApiTeste[]>([]);
  const [error, setError] = useState<Boolean>(false);

  useEffect(() => {
    post
      .get('?vote_gte=100')
      .then((post) => {
        setError(false);
        setData(post.data);
      })
      .catch((err) => {
        const status = err.response.status;
        if (status === 404) console.debug('erro 404');
        else console.debug('internal server error');
        setError(true);
      });
  }, []);

  return (
    <>
      <Header />
      <Container>
        <MT />
        <Body>
          <Content>

            {
              (function hasError() {
                if (error) {
                  return <span>não foi possível carregar as publicações. <a href="/">tentar novamente</a></span>
                } else if (data.length === 0) {
                  return <span>carregando...</span>
                } else {
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
                  ))
                }
              })()
            }

            {/* {error ? (
              <h4>
                ocorreu um erro ao tentar carregar os dados. Tente novamente
                mais tarde.
              </h4>
            ) : data.length == 0 ? (
              <h3>carregando posts...</h3>
            ) : ([
              data.map((i) => (
                <Card
                  subreddit={i.subreddit}
                  username={i.user}
                  comment={i.comment}
                  vote={i.vote}
                  time={i.createdAt}
                  content={i.content}
                  key={i.id}
                />
              )),
              <button key="loadmore">carregar mais...</button>
            ])} */}
          </Content>

          <Side></Side>
        </Body>
      </Container>
    </>
  );
};

export default Main;

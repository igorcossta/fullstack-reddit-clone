import React from 'react';
import Card from '../../component/Card';
import Header from '../../component/Header';
import { Body, Container, Content, MT, Side } from './style';

const Main: React.FC = () => {
  return (
    <>
      <Header />
      <Container>
        <MT />
        <Body>
          <Content>
            <Card
              subreddit="WonderfulWorld"
              username="anonymous"
              time="10 minutes ago"
              vote={12.4}
              comment={123}
              content="yes, we can!"
            />
          </Content>

          <Side></Side>
        </Body>
      </Container>
    </>
  );
};

export default Main;

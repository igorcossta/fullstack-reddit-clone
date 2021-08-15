import React, { useState, useEffect } from 'react';

import { PostProps } from '../../@types/subreddit.type';
import { RedditAPI } from '../../axios/reddit.api';
import { Card } from '../../component';
import { Container, Section, Aside } from './styles';

const Home: React.FC = () => {
  const [posts, setPosts] = useState<PostProps[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);

  useEffect(() => {
    async function fetch() {
      await RedditAPI.get('/api/post?size_gt=500')
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
      <Aside>
        <h1>top subreddits</h1>
      </Aside>
      <Section>
        {loading ? (
          <h6>carregando</h6>
        ) : (
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
        )}
      </Section>
    </Container>
  );
};

export default Home;

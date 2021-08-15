import React from 'react';
import { Link } from 'react-router-dom';

import { SubredditProps } from '../../@types/subreddit.type';
import { Container } from './styles';

const SubredditCard: React.FC<SubredditProps> = ({ name, description, numberOfPosts }) => {
  return (
    <Container>
      <h1>{name}</h1>
      <p>{description}</p>
      <span>
        Number of Posts <strong>{numberOfPosts}</strong>
      </span>
      <div>
        <Link to={`/r/${name}`}>Go to</Link>
      </div>
    </Container>
  );
};

export default SubredditCard;

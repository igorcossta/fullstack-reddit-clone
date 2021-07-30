import React from 'react';

import {
  CardBody,
  CardContent,
  CardInformation,
  CardToolBar,
  CardVote,
  CommentIcon,
  Container,
  DownVote,
  SaveIcon,
  ShareIcon,
  SubredditIcon,
  UpVote,
} from './style';

interface Props {
  comment: number;
  content: string;
  subreddit: string;
  time: string;
  username: string;
  vote: number;
}

const Card: React.FC<Props> = ({ comment, content, subreddit, time, username, vote }) => {
  return (
    <Container>
      <CardVote>
        <UpVote />
        <small className="vote-count">{vote}</small>
        <DownVote />
      </CardVote>

      <CardBody>
        <CardInformation>
          <SubredditIcon />
          <button type="button">r/{subreddit}</button>
          <span style={{ fontSize: '8px', margin: '0 2.5px 0 2.5px' }}>•</span>
          <span>
            Posted by <span className="username">u/{username}</span> {time}
          </span>
        </CardInformation>

        <CardContent>{content}</CardContent>

        <CardToolBar>
          <button type="button">
            <CommentIcon /> {comment} Comments
          </button>

          <button type="button">
            <ShareIcon /> Share
          </button>

          <button type="button">
            <SaveIcon /> Save
          </button>
        </CardToolBar>
      </CardBody>
    </Container>
  );
};

export default Card;

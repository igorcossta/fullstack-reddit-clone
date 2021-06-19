import React from 'react';
import {
  Container,
  CardVote,
  CardBody,
  CardInformation,
  CardContent,
  CardToolBar,
  UpVote,
  DownVote,
  SubredditIcon,
  CommentIcon,
  ShareIcon,
  SaveIcon,
  ShareButton,
  SaveButton,
} from './style';

interface Props {
  comment: number;
  content: string;
  subreddit: string;
  time: string;
  username: string;
  vote: number;
}

const Card: React.FC<Props> = ({
  comment,
  content,
  subreddit,
  time,
  username,
  vote,
}) => {
  return (
    <Container>
      <CardVote>
        <UpVote />
        <small className="vote-count">{vote}k</small>
        <DownVote />
      </CardVote>

      <CardBody>
        <CardInformation>
          <SubredditIcon />
          <a href="/" className="subreddit-name">
            r/{subreddit}
          </a>
          <span style={{ fontSize: '8px', margin: '0 2.5px 0 2.5px' }}>•</span>
          <span>
            Posted by <span className="username">u/{username}</span> {time}
          </span>
        </CardInformation>

        <CardContent>{content}</CardContent>

        <CardToolBar>
          <a href="/">
            <CommentIcon /> {comment} Comments
          </a>

          <ShareButton>
            <ShareIcon /> Share
          </ShareButton>

          <SaveButton>
            <SaveIcon /> Save
          </SaveButton>
        </CardToolBar>
      </CardBody>
    </Container>
  );
};

export default Card;

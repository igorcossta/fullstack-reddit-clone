import styled, { css } from 'styled-components';
import {
  AiOutlineArrowUp,
  AiOutlineArrowDown,
  AiOutlinePushpin,
} from 'react-icons/ai';
import { FcGlobe } from 'react-icons/fc';
import { BsChatSquare, BsArrow90DegRight } from 'react-icons/bs';

const Icon = css`
  font-size: 16px;
  margin-right: 6px;
`;

export const UpVote = styled(AiOutlineArrowUp)`
  &:hover {
    fill: tomato;
  }
`;
export const DownVote = styled(AiOutlineArrowDown)`
  &:hover {
    fill: hsl(214, 100%, 50%);
  }
`;
export const SubredditIcon = styled(FcGlobe)`
  ${Icon}
  margin-right: 3px;
`;
export const CommentIcon = styled(BsChatSquare)`
  ${Icon}
`;
export const ShareIcon = styled(BsArrow90DegRight)`
  ${Icon}
`;
export const SaveIcon = styled(AiOutlinePushpin)`
  ${Icon}
`;

export const Container = styled.div`
  border: 1px solid rgba(26, 26, 27, 0.1);
  cursor: pointer;
  display: grid;
  grid-template-areas: 'vote content';
  grid-template-columns: auto 1fr;

  &:not(:last-child) {
    margin-bottom: 10px;
  }

  &:hover {
    border-color: rgba(26, 26, 27, 0.3);
  }
`;

export const CardVote = styled.div`
  align-items: center;
  background-color: rgba(255, 255, 255, 0.8);
  display: flex;
  flex-direction: column;
  gap: 5px;
  grid-area: vote;
  padding: 8px 5px;

  .vote-count {
    font-size: 12px;
  }
`;

export const CardBody = styled.div`
  background-color: hsl(0, 100%, 100%);
  grid-area: content;
`;

export const CardInformation = styled.div`
  align-items: center;
  color: hsl(200, 2%, 48%);
  display: inline-flex;
  font-size: 12px;
  margin-bottom: 6px;
  margin-left: 6px;

  a.subreddit-name {
    color: hsl(0, 0%, 10%);
    font-weight: 700;
    line-height: 20px;
    text-decoration: none;
    vertical-align: baseline;

    &:hover {
      text-decoration: underline;
    }
  }

  .username {
    margin: 0 3px;

    &:hover {
      text-decoration: underline;
    }
  }
`;

export const CardContent = styled.div`
  margin: 0 6px 0 6px;
  text-align: justify;
`;

export const CardToolBar = styled.div`
  display: flex;
  flex-direction: row;
  font-size: 11.5px;
  font-weight: 600;
  margin-top: 3px;
  padding: 0 8px 0 4px;

  a {
    align-items: center;
    color: hsl(0, 0%, 53%);
    display: flex;
    margin-bottom: 3px;
    margin-right: 3px;
    padding: 8px;
    text-decoration: none;

    &:hover {
      background-color: rgba(26, 26, 27, 0.1);
      border-radius: 3px;
    }
  }
`;

const Button = css`
  align-items: center;
  background: none;
  border: none;
  color: hsl(0, 0%, 53%);
  cursor: pointer;
  display: flex;
  font-weight: 600;
  margin-bottom: 3px;
  margin-right: 3px;
  padding: 8px;
`;

export const ShareButton = styled.button`
  ${Button}
  &:hover {
    background-color: rgba(26, 26, 27, 0.1);
    border-radius: 3px;
  }
`;
export const SaveButton = styled.button`
  ${Button}
  &:hover {
    background-color: rgba(26, 26, 27, 0.1);
    border-radius: 3px;
  }
`;

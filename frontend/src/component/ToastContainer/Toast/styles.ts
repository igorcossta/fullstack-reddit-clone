import styled, { css } from 'styled-components';

const variations = {
  success: css`
    background: #5cb85c;
  `,
  danger: css`
    background: #d9534f;
  `,
  info: css`
    background: #5bc0de;
  `,
};

interface Props {
  type?: 'success' | 'danger' | 'info';
}

export const Container = styled.div<Props>`
  ${(props) => variations[props.type || 'info']}
  overflow: hidden;
  padding: 16px;
  margin-bottom: 3px;
  width: 360px;
  max-height: 100px;
  border-radius: 3px;
  display: flex;
  align-items: center;

  &:hover {
    cursor: pointer;
  }
`;

export const Title = styled.h6`
  font-weight: 500;
  font-size: 16px;
`;

export const Description = styled.p`
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
`;

export const Icon = styled.span`
  margin-right: 15px;
`;

import styled, { css } from 'styled-components';

const positions = {
  topRight: css`
    top: 12px;
    right: 12px;
  `,
  topLeft: css`
    top: 12px;
    left: 12px;
  `,
  bottomRight: css`
    bottom: 12px;
    right: 12px;
  `,
  bottomLeft: css`
    bottom: 12px;
    left: 12px;
  `,
};

interface Props {
  position?: 'topRight' | 'topLeft' | 'bottomRight' | 'bottomLeft';
}

export const Container = styled.div<Props>`
  ${(props) => positions[props.position || 'topRight']}
  font-size: 14px;
  position: fixed;
  z-index: 9999;
`;

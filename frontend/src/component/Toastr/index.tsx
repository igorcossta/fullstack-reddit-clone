/* eslint-disable @typescript-eslint/no-explicit-any */
import React from 'react';
import {
  IoAlertOutline,
  IoBanOutline,
  IoCheckmarkDoneOutline,
  IoInformationOutline,
  IoWarningOutline,
} from 'react-icons/io5';
import { toast } from 'react-toastify';

import 'react-toastify/dist/ReactToastify.css';
import { Container } from './styles';

interface Props {
  message: string;
  type?: 'danger' | 'info' | 'success' | 'warn' | 'default';
}

const icons = {
  danger: <IoBanOutline size={20} />,
  default: <IoAlertOutline size={20} />,
  info: <IoInformationOutline size={20} />,
  success: <IoCheckmarkDoneOutline size={20} />,
  warn: <IoWarningOutline size={20} />,
};

const options = {
  position: toast.POSITION.TOP_RIGHT,
};

const ToastrComp: React.FC<Props> = ({ message, type }) => {
  return (
    <Container>
      {icons[type || 'default']}
      <span>{message}</span>
    </Container>
  );
};

const dangerToastr = (message: string): any => {
  toast.error(<ToastrComp message={message} type="danger" />, options);
};

const infoToastr = (message: string): any => {
  toast.info(<ToastrComp message={message} type="info" />, options);
};

const successToastr = (message: string): any => {
  toast.success(<ToastrComp message={message} type="success" />, options);
};

const warnToastr = (message: string): any => {
  toast.warn(<ToastrComp message={message} type="warn" />, options);
};

const defaultToastr = (message: string): any => {
  toast(<ToastrComp message={message} />, options);
};

const Toastr = {
  danger: dangerToastr,
  info: infoToastr,
  success: successToastr,
  warning: warnToastr,
  default: defaultToastr,
};

export default Toastr;

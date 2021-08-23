interface PostPayLoad {
  description: string;
  postName: string;
  subredditName?: string;
  url?: string;
}

interface PostProps {
  id: number;
  subredditName: string;
  postName: string;
  description: string;
  username: string;
  voteCount: number;
  commentCount: number;
  upVote: boolean;
  downVote: boolean;
  duration: string;
  url: string;
}

export type { PostProps, PostPayLoad };

interface SubredditPayLoad {
  name: string;
  description: string;
}

interface SubredditProps {
  name: string;
  description: string;
  numberOfPosts: number;
}

interface PostProps {
  subreddit: string;
  username: string;
  time: string;
  comment: number;
  vote: number;
  content: string;
}

export type { SubredditProps, SubredditPayLoad, PostProps };

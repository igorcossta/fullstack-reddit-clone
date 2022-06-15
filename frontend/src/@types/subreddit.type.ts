interface SubredditPayLoad {
  name: string;
  description: string;
}

interface SubredditProps {
  subredditId?: number;
  name: string;
  description: string;
  numberOfPosts: number;
}

export type { SubredditProps, SubredditPayLoad };

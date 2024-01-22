export interface CommentPayload{
  id: number;
  text: string;
  creationDate: Date;
  postedBy: string;
  commentedOn: number;
  repliesTo: number;
  reactions: number;
}

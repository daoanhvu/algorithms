import { User } from './user.model';

export interface Group {
    owner: User;
    name: string;
    description: string;
    members?: User[];
    username: string;
}

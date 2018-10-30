import { User } from './user.model';

export interface Group {
    id: number;
    owner: User;
    name: string;
    description: string;
    members?: User[];
}

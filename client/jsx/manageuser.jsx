import React from 'react';
import ReactDOM from 'react-dom';
import UserList from './userlist.jsx';
import UpdateUserPane from './updateuser.jsx';

class ManagedUserForm extends React.Component {
    constructor(props) {
        super(props);
    }
    
    componentDidMount() {
        var self = this;
    }
    
    render() {
        var self = this;
        var ele = (
                <div>   
                    <UpdateUserPane />                 
                    <UserList />
                </div>
                );
        
        return ele;
    }
};

export default ManagedUserForm;
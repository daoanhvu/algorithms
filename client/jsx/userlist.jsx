import React from 'react';
import ReactDOM from 'react-dom';
import UpdateUserPane from './updateuser.jsx';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../static/css/app.css';

class UserList extends React.Component {
    constructor(props) {
        super(props);
        var self = this;
        this.state = {users: []};
    }
    
    componentDidMount() {
        var self = this;
        
        fetch('http://localhost:8090/bkda/api/user/')
        .then(result => {
            return result.json();
        }).then( (json) => {
            self.setState({users: json});
        });
    }
    
    onEditItemClick(item) {
        
    }
    
    onRemoveItemClick(item) {
        
    }
    
    render() {
        var self = this;
        
        var userRows = this.state.users.map( (u, idx) =>
            <tr>
                <td>{u.id}</td>
                <td>{u.userName}</td>
                <td>{u.firstName}</td>
                <td>{u.lastName}</td>
                <td>{u.startDate}</td>
                <td><button type='button' onClick={() => self.onEditItemClick(u)} className='btn btn-success custom-width'>Edit</button></td>
                <td><button type='button' onClick={() => self.onRemoveItemClick(u)} className='btn btn-danger custom-width'>Remove</button></td>
            </tr>
        );
        
        var ele = (
                <div className='generic-container'>
                    <div className='panel panel-default'>
                        <div className='panel-heading'><span className='lead'>List of users</span></div>
                        <div className='panel-body'>
                            <div className='table-responsive'>
                                <table className='table table-hover'>
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>User Name</th>
                                            <th>First Name</th>
                                            <th>Last Name</th>
                                            <th>Start Date</th>
                                            <th width='100'></th>
                                            <th width='100'></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {userRows}
                                    </tbody>
                                </table>        
                            </div>
                        </div>
                    </div>
                </div>
                );
        
        return ele;
    }
};

export default UserList;
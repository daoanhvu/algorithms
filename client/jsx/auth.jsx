import React, { Component } from 'react';
import { BrowserRouter, Link, Route, Redirect } from 'react-router-dom';
import LoginPane from './login.jsx';
import ManagedUserForm from './manageuser.jsx';

//Fake components, will be replaced
const Public = () => <div><h2>This is public area</h2></div>;
//const Protected = () => <div><h2>This is PROTECTED area</h2></div>;

const PrivateRoute = ({component: Component, authed, ...rest}) => (
      <Route {...rest} render={(props)=>authed === false? 
              <Component {...props} />:<Redirect to={{pathname:'/bkda/login', state: {from: props.location}}} />
      } />
);

class AuthExaminer extends React.Component {
    
    render() {
        var self = this;
        return (
                <BrowserRouter>
                    <div>
                        <ul>
                            <li><Link to='/bkda/public' >Go to public page</Link></li>
                            <li><Link to='/bkda/protected' >Go to protected page</Link></li>
                        </ul>
                        <Route path='/bkda/public' component={Public} />
                        <Route path='/bkda/login' component={LoginPane} />
                        <PrivateRoute authed={false} path='/bkda/protected' component={ManagedUserForm} />
                    </div>
                </BrowserRouter>
                );
    }
};

export default AuthExaminer;
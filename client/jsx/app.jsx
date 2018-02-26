import React from 'react';
import ReactDOM from 'react-dom';
import AuthExaminer from './auth.jsx';

class App extends React.Component {
    
    constructor(props) {
        super(props);
        var self = this;
        self.state = {users: []};
        
    }
    
    render() {
        var self = this;
        return (
           <div>
                <h1>THIS IS FROM REACT!!!</h1>
                <AuthExaminer />
           </div>
                );
    }
};

export default App;
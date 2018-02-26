import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../static/css/app.css';

export default class LoginPane extends React.Component {
    
    onSubmitForm() {
        
    }
    
    render() {
        var self = this;
        return (
                <div className='container'>
                    <div className='row' id='pwd-container'>
                        <div className='col-md-4'></div>
                        
                        <div className='col-md-4'>
                            <section className='login-form'>
                                <form method='post' action='http://localhost:8090/bkda/api/login' role='login' >
                                    <img src="http://i.imgur.com/RcmcLv4.png" className="img-responsive" alt="" />
                                    <input type="email" name="email" placeholder="Email" required className="form-control input-lg" value="joestudent@gmail.com" />
                                    <input type="password" className="form-control input-lg" id="password" placeholder="Password" required="" />
                                    <div class="pwstrength_viewport_progress"></div>
                                    <button type="submit" name="go" onClick={self.onSubmitForm()} className="btn btn-lg btn-primary btn-block">Sign in</button>
                                    <div>
                                      <a href="#">Create account</a> or <a href="#">reset password</a>
                                    </div>
                                </form>
                                <div class="form-links"><a href="#">www.bkda.com</a></div>
                            </section>
                        </div>
                    </div>
                    <p>This is login Form :)</p>
                </div>
                );
    }
};


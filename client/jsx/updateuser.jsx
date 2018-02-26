import React, { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../static/css/app.css';

const FormAlertMessage = ({component: Component, isError, ...rest}) => (
        isError === 0?<div className='alert alert-success' role='alert' >Save user successfully!</div>
        :<div className='alert alert-danger' role='alert' >Save user FAILED!!!</div>
);

export default class UpdateUserPane extends React.Component {
    
    constructor(props) {
        super(props);
        var self = this;
        self.state = {successMessage:'User saved successfully!',
                errorMessage: 'FAILED', hasError: 0, user:{}};
    }
    
    onSaveUserClick() {
        alert('You click save button!');
    }

    onResetClick() {
        
    }
    
    render() {
        var self = this;
        return (
            <div className='formcontainer'>
                <FormAlertMessage isError={self.state.hasError} />
                <form method='POST' name='myForm' className='form-horizontal'>
                    <input type='hidden' value={self.state.user.id} />
                    <div className='form-group'>
                        <div className='form-group col-md-12'>
                            <label className='col-md-2 control-label' for="username">User name</label>
                            <div className='col-md-7'>
                                <input type='text' value={self.state.user.userName} id='username' className='username form-control input-sm' placeholder='Enter your name' required />
                            </div>
                        </div>                            
                    </div>
                    <div className='form-group'>
                        <div className='form-group col-md-12'>
                            <label className='col-md-2 control-label' for='firstName'>First name</label>
                                <div className='col-md-7'>
                                    <input type='text' value={self.state.user.firstName} id='firstName' className='form-control input-sm' placeholder='Enter your first name.' />
                                </div>
                        </div>
                    </div>
                    <div className='form-group'>
                        <div className='form-group col-md-12'>
                            <label className='col-md-2 control-label' for='lastName'>Last name</label>
                                <div className='col-md-7'>
                                    <input type='text' value={self.state.user.lastName} id='lastName' className='form-control input-sm' placeholder='Enter your last name.' />
                                </div>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-sm-3">Gender</label>
                        <div class="col-sm-6">
                            <div class="row">
                                <div class="col-sm-4">
                                    <label class="radio-inline">
                                        <input type="radio" id="femaleRadio" value="Female" />Female
                                    </label>
                                </div>
                                <div class="col-sm-4">
                                    <label class="radio-inline">
                                        <input type="radio" id="maleRadio" value="Male" />Male
                                    </label>
                                </div>
                                <div class="col-sm-4">
                                    <label class="radio-inline">
                                        <input type="radio" id="uncknownRadio" value="Unknown" />Unknown
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div className='form-group'>
                        <div className='form-actions floatRight'>
                            <button type='button'  onClick={self.onSaveUserClick } className='btn btn-primary btn-sm'>Save</button>
                            <button type='button' onClick={self.props.onResetClick} className='btn btn-warning btn-sm'>Reset Form</button>
                        </div>
                    </div>
                </form>
            </div>
        );
    }
};


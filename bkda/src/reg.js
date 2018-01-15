import React from 'react';
import fetch from 'isomorphic-fetch';
import './reg.css';

function postAndWait(formData) {
		return fetch('http://localhost:9090/bkdasvr/user/user/new',
			{
				method: 'POST',
				body: JSON.stringify(formData),
				headers: {
					'Access-Control-Allow-Origin': '*',
					'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE, OPTIONS',
					'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token',
					'Content-Type': 'application/json'
				}
			}
		).then(res => res.json()).catch(err => {
			alert('FAILED!!!' + err);
		});
	}

class RegForm extends React.Component {
	constructor(props) {
		super(props);

		this.state = {
			firstname: ' ',
			lastname: ' ',
			username: ' ',
			email: ' '
		};

		this.handleChange = this.handleChange.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	handleChange(event) {

	}

	handleSubmit(event) {
		event.preventDefault();

		var formData = {
			firstname: this.refs.firstname.value,
			lastname: this.refs.lastname.value,
			username: this.refs.username.value,
			email: this.refs.email.value,
			password: this.refs.password.value
		};

		/* alert("Fullname: " + formData.fullname); */

		/** Post data to server  */
		postAndWait(formData);
	}

	render() {
		return (
			<div id='login-box'>
				<div className='left'>
					<form id='regform' onSubmit={this.handleSubmit}>
						<div>
							<input type='text' ref='firstname' name='firstname' placeHolder="Firstname"/>
							<input type='text' ref='lastname' name='lastname' placeHolder="Lastname"/>
						</div>
						<input type='text' ref='username' name='username' onChange={this.handleChange} placeHolder="Username" />
						<input type='text' ref='email' name='email' onChange={this.handleChange} placeHolder="Email" />
						<input type='password' ref='password' name='password' onChange={this.handleChange} placeHolder="Password" />
						<input type='password' onChange={this.handleChange} placeHolder="Re-type password" />
						<input type='submit' name='signup_submit' value='Register' />
					</form>
				</div>
				<div className='right'>
					<span className='loginwith'>Sign in with</span>
					<button className="social-signin facebook">Log in with facebook</button>
    				<button className="social-signin twitter">Log in with Twitter</button>
    				<button className="social-signin google">Log in with Google+</button>
				</div>
				<div className='or'>OR</div>
			</div>
		);
	}
}

export default RegForm;
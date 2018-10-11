<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">User </span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.user.id" />
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="uname">Name</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.user.userName" id="uname" class="username form-control input-sm" placeholder="Enter your name" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="firstName">First name</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.user.firstName" id="firstName" class="form-control input-sm" placeholder="Enter your first name." />
	                        </div>
	                    </div>
	                </div>
	
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="lastName">Last Name</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.user.lastName" id="lastName" class="form-control input-sm" placeholder="Enter your last name." />
	                        </div>
	                    </div>
	                </div>
	                
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="email">Email</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.user.email" id="email" class="form-control input-sm" placeholder="Enter your email." />
	                        </div>
	                    </div>
	                </div>
	                
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="phone">Phone number</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.user.phoneNumber" id="phone" class="form-control input-sm" placeholder="Enter your phone number." />
	                        </div>
	                    </div>
	                </div>
	                
	                <div class="row">
	                	<div class="form-group col-md-12">
	                		<label class="col-md-2 control-lable" for="sex">Gender</label>
	                		<div class="col-md-7">
	                			<label class="radio-inline"><input type="radio" id="sex" name="sex" value="F" ng-model="ctrl.user.sex" >Female</label>
	                			<label class="radio-inline"><input type="radio" id="sex" name="sex" value="M" ng-model="ctrl.user.sex" >Male</label>
	                		</div>
	                	</div>
	                </div>

	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <input type="submit"  value="{{!ctrl.user.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Users </span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th>ID</th>
		                <th>User Name</th>
		                <th>First Name</th>
		                <th>Last Name</th>
		                <th>Start Date</th>
		                <th width="100"></th>
		                <th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="u in ctrl.getAllUsers()">
		                <td>{{u.id}}</td>
		                <td>{{u.userName}}</td>
		                <td>{{u.firstName}}</td>
		                <td>{{u.lastName}}</td>
		                <td>{{u.startDate}}</td>
		                <td><button type="button" ng-click="ctrl.editUser(u.id)" class="btn btn-success custom-width">Edit</button></td>
		                <td><button type="button" ng-click="ctrl.removeUser(u.id)" class="btn btn-danger custom-width">Remove</button></td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
		</div>
    </div>
</div>
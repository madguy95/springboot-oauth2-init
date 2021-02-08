import { Component } from '@angular/core';
import {AppService, Foo, Employee} from './app.service'

@Component({
  selector: 'foo-details',
  providers: [AppService],  
  template: `<div class="container">
  	<h1 class="col-sm-12">Employee with admin role</h1>
    <div class="col-sm-12">
        <label class="col-sm-3">ID</label> <span>{{employee.email}}</span>
        <label class="col-sm-3">ID</label> <span>{{employee.name}}</span>
    </div>
    <h1 class="col-sm-12">Foo Details</h1>
    <div class="col-sm-12">
        <label class="col-sm-3">ID</label> <span>{{foo.id}}</span>
    </div>
    <div class="col-sm-12">
        <label class="col-sm-3">Name</label> <span>{{foo.name}}</span>
    </div>
    <div class="col-sm-12">
        <button class="btn btn-primary" (click)="getFoo()" type="submit">New Foo</button>        
    </div>
</div>`
})

export class FooComponent {
    public foo = new Foo(1,'sample foo');
    
    public employee = new Employee('a','sample foo');
    
    private foosUrl = 'http://localhost:8082/oauth-server/foos/';  
    
    private employeeURL = 'http://localhost:8081/oauth-server/employee';  

    constructor(private _service:AppService) {}

	ngOnInit() {
		this._service.getResource(this.employeeURL)
         .subscribe(
                     data => this.employee = data,
                     error =>  this.foo.name = 'Error');
	}
    getFoo(){
        this._service.getResource(this.foosUrl+this.foo.id)
         .subscribe(
                     data => this.foo = data,
                     error =>  this.foo.name = 'Error');
    }
}

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Cookie } from 'ng2-cookies';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    loginForm: FormGroup;
    loading = false;
    submitted = false;
    returnUrl: string;

    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private _http: HttpClient
    ) {
    }

    ngOnInit() {
        this.loginForm = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', Validators.required]
        });

        // get return url from route parameters or default to '/'
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    }

    // convenience getter for easy access to form fields
    get f() { return this.loginForm.controls; }

    onSubmit() {
        this.submitted = true;

        // stop here if form is invalid
        if (this.loginForm.invalid) {
            return;
        }

        this.loading = true;
        let headers = new HttpHeaders({'Content-type': 'application/json; charset=utf-8'});
        //  this._http.delete('http://localhost:8082/auth-server/token/revoke', { headers: headers })
        // .subscribe(
        //   data => {
        //     Cookie.delete('access_token');
        //     window.location.reload();
        //   },
        //   err => alert('Invalid Credentials')
        // ); 

        this._http.post('http://localhost:8081/oauth-server/login' + Cookie.get('access_token'), { username: this.f.username.value,password: this.f.password.value }, { headers: headers })
            .subscribe(
              data => {
                
              },
              err => {
                
              }
            ); 
        }
}

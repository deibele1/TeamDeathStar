import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EventModel } from '../Models/EventModel';
import { PredictionModel } from '../Models/PredictionModel';
import { UserModel } from '../Models/UserModel';
import { HomeComponent } from '../home/home.component';

@Injectable()
export class AppServices {

  serviceURL: string = 'http://18.191.179.31:9090/';  

  constructor(private http: HttpClient) {}

  getEvents(): Observable<EventModel[]> {
    return this.http.get<EventModel[]>(`${this.serviceURL}`, {responseType: 'json'});
  }

  getPredictions(event: EventModel): Observable<PredictionModel> {
    return this.http.get<PredictionModel>(`${this.serviceURL}/event/${event.eventId}`, {responseType: 'json'});
  }

  loginIn(username: string, password: string): Observable<UserModel> {
    let temp = {'username': username, 'password': password}
    return this.http.post<UserModel>(`${this.serviceURL}user/login`, temp, {responseType: 'json'});
  }
  
  signup(username: string, password: string, first: string, last: string, email: string,
          dob: Date, ans1: string, ans2: string, ans3: string, questionNum1: number,
          questionNum2: number, questionNum3: number): Observable<UserModel> {
   let temp = {'username': username, 'password': password, 'first': first, 'last': last,
      'email': email, 'dob': dob.toString(), 'ans1': 'a', 'ans2': 'a', 'ans3': 'a',
      'questionNum1': 0, 'questionNum2': 0,'questionNum3': 0,}
    return this.http.post<UserModel>(`${this.serviceURL}user/add`, temp, {responseType: 'json'});
  }

  addEvent(eventDate: Date, eventName: string, categoryNumber: number, description: string,
          username: string, password: string): Observable<EventModel>{
    let header = new HttpHeaders({'username': username, 'password': password});
    let temp = {'advent': eventDate.toString(), 'eventName': eventName, 'categoryNumber': 
              categoryNumber, 'description': description};
    return this.http.post<EventModel>(`${this.serviceURL}event/add`, temp, 
          {responseType: 'json', headers: header});
  }
  predict(outcome:Boolean, eventId: number, username: string,) {
    let header = new HttpHeaders({'username':username});
    let temp = {'eventId':eventId,'predicted' : outcome};
    console.log("before Post")
    this.http.post<string>(`${this.serviceURL}pred/add`,temp,{responseType: 'json', headers: header}).subscribe();
  }

}
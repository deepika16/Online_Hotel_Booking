import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Center } from './center';
import { Comments } from './comments';

@Injectable({
  providedIn: 'root'
})
export class CenterService {

  private baseUrl = 'http://127.0.0.1:8080/api/v1/center';

  constructor(private http: HttpClient) { }

  getCenter(id: number): Observable<Center> {
    return this.http.get<Center>(`${this.baseUrl}/${id}`);
  }
  getCenterDetails(id: number): Observable<Center> {
    return this.http.get<Center>(`${this.baseUrl}/center-details/${id}`);
  }

  createCenter(center: FormData): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, center);
  }

  saveComment(comment: Comments): Observable<Object> {
    return this.http.post(`http://127.0.0.1:8080/api/v1/comments`, comment);
  }

  updateCenter(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  updateUploadCenter(id: number, center: FormData): Observable<Object> {
    return this.http.post(`${this.baseUrl}/${id}`, center);
  }

  deleteCenter(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  deleteComments(id: number): Observable<any> {
    return this.http.delete(`http://127.0.0.1:8080/api/v1/comments/${id}`, { responseType: 'text' });
  }

  getCenterList(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/all-center/${id}`);
  }

  filterCenterList(search: String): Observable<any> {
    return this.http.get(`${this.baseUrl}/all-search/${search}`);
  }

  getCommentsList(id: number): Observable<any> {
    return this.http.get(`http://127.0.0.1:8080/api/v1/comments/all-users-comments/${id}`);
  }

  getAllCenter(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  getAllComments(id: number): Observable<any> {
    return this.http.get(`http://127.0.0.1:8080/api/v1/comments/all-comments/${id}`);
  }
}

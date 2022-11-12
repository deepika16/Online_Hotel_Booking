import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Location } from './location';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  private baseUrl = 'http://127.0.0.1:8080/api/v1/categories';

  constructor(private http: HttpClient) { }

  getLocation(id: number): Observable<Location> {
    return this.http.get<Location>(`${this.baseUrl}/${id}`);
  }

  createLocation(location: FormData): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, location);
  }

  updateLocation(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  updateUploadLocation(id: number, location: FormData): Observable<Object> {
    return this.http.post(`${this.baseUrl}/${id}`, location);
  }

  deleteLocation(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getCategoriesList(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/all-categories/${id}`);
  }

  getAllCategories(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}

// demo.interceptor.ts

import { isPlatformBrowser } from '@angular/common';
import { HttpInterceptorFn } from '@angular/common/http';
import { PLATFORM_ID, inject } from '@angular/core';

export const JwtInterceptorService: HttpInterceptorFn = (req, next) => {
  // const loginToken = sessionStorage.getItem("token");
  
  const platformId = inject(PLATFORM_ID);
  var loginToken: String | null = 'default';
  loginToken = isPlatformBrowser(platformId) ? sessionStorage.getItem('token') : null;

  console.log(loginToken);
  
  // Clone the request and add the authorization header
  if (loginToken) {
    const authReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${loginToken}`
      }
    });
    return next(authReq);
  }
  

  // Pass the cloned request with the updated header to the next handler
  return next(req);
};
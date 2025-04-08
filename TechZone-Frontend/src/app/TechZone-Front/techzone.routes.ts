import { Component } from '@angular/core';
import { Routes } from '@angular/router';
import { TechzoneLayoutComponent } from './layouts/TechzoneLayout/TechzoneLayout.component';
import { HomePagesComponent } from './pages/HomePages/HomePages.component';
import { NotFoundPageComponent } from '../shared/pages/notFoundPage/notFoundPage.component';

export const TechZoneRoutes: Routes = [

  {

    path:'',
    component:TechzoneLayoutComponent,
    children:[
      {
        path: '',
        component: HomePagesComponent
      }

    ]

    }




]

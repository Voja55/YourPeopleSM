import { Component } from '@angular/core';
import {group} from "@angular/animations";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {

    protected readonly group = group;
}

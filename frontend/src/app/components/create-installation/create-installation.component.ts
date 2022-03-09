import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { InstallAtService } from 'src/app/service/install-at.service';

@Component({
  selector: 'app-create-installation',
  templateUrl: './create-installation.component.html',
  styleUrls: ['./create-installation.component.scss']
})
export class CreateInstallationComponent implements OnInit {

  createInstallForm!: FormGroup;

  constructor(private readonly formBuilder: FormBuilder,
    public installAtService: InstallAtService,
    public router: Router) { }

  async ngOnInit(): Promise<void> {
  }

}

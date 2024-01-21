import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupExploreComponent } from './group-explore.component';

describe('GroupExploreComponent', () => {
  let component: GroupExploreComponent;
  let fixture: ComponentFixture<GroupExploreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GroupExploreComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GroupExploreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

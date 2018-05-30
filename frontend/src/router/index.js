import Vue from 'vue';
import Router from 'vue-router';
import Index from '@/components/Index';
import Shedule from '@/components/Shedule';
import Report from '@/components/Report';
import IssueView from '@/components/Issue/IssueView';
import CreateIssue from "@/components/Issue/CreateIssue";
import IssueUniq from "@/components/Issue/IssueUniq";
import Worker from "@/components/Worker/WorkerView";
import CreateWorker from "@/components/Worker/CreateWorkerView";
import Position from "@/components/Position/PositionView";
import CreatePosition from "@/components/Position/CreatePositionView";
import Status from "@/components/Status/StatusView";
import CreateStatus from "@/components/Status/CreateStatusView";
import Store from '../store/index';

Vue.use(Router)


const router = new Router({
  routes: [
    {
      path: '/index',
      name: 'Index',
      component: Index
    },
    {
      path: '/issue',
      name: 'IssueView',
      component: IssueView
    },
    {
      path: "/createIssue",
      name: "CreateIssue",
      component: CreateIssue
    },
    {
      path: "/issue/:id",
      name: "IssueUniq",
      component: IssueUniq
    },
    {
      path: "/worker",
      name: "Worker",
      component: Worker
    },
    {
      path: "/createWorker",
      name: "CreateWorker",
      component: CreateWorker
    },
    {
      path: "/position",
      name: "Position",
      component: Position
    },
    {
      path: "/createPosition",
      name: "CreatePosition",
      component: CreatePosition
    },
    {
      path: "/status",
      name: "Status",
      component: Status
    },
    {
      path: "/createStatus",
      name: "CreateStatus",
      component: CreateStatus
    },
    {
      path: "/report",
      name: "Report",
      component: Report
    },
    {
      path: "/schedule",
      name: "Shedule",
      component: Shedule
    }
  ]
})


export default router;

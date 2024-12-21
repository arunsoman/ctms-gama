export * from './loginController.service';
import { LoginControllerService } from './loginController.service';
export * from './userController.service';
import { UserControllerService } from './userController.service';
export const APIS = [LoginControllerService, UserControllerService];

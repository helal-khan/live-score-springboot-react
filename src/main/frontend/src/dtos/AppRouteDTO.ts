export default interface AppRouteDTO {
  key?: string;
  path?: string;
  exact?: boolean;
  strict?: boolean;
  restricted?: boolean;
  component: any;
  routes?: AppRouteDTO[];
}

import { notification } from "antd";
import { IconType, NotificationPlacement } from "antd/lib/notification";
import Constant from "../config/Constant";

export default function Notify({
  type,
  placement = "bottomRight",
  duration = Constant.NOTIFICATION_DURATION,
  message,
  description,
}: {
  type: IconType;
  placement?: NotificationPlacement;
  duration?: number;
  message: string;
  description?: string;
}) {
  return notification[type]({ placement, duration, message, description });
}

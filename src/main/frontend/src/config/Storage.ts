const Storage = {
  getData: (key: string, isMap?: boolean) => {
    const data =
      process.env.REACT_APP_STORAGE === "local"
        ? localStorage.getItem(key)
        : localStorage.getItem(key);
    return isMap
      ? data
        ? new Map(JSON.parse(data))
        : new Map()
      : data
      ? JSON.parse(data)
      : null;
  },
  setData: (key: string, data: any, isMap?: boolean) => {
    const stringData = JSON.stringify(
      isMap ? Array.from(data.entries()) : data
    );
    return process.env.REACT_APP_STORAGE === "local"
      ? localStorage.setItem(key, stringData)
      : localStorage.setItem(key, stringData);
  },
  deleteData: (key: string) => {
    process.env.REACT_APP_STORAGE === "local"
      ? localStorage.removeItem(key)
      : localStorage.removeItem(key);
  },
};
export default Storage;

import * as moment from "moment"

export const formatDateTime = (timestamp: number) => {
    return moment.unix(timestamp).format("YYYY-MM-D HH:mm:ss")
}
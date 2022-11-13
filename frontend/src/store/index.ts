import { getMe, getUserById, User } from "../api"

class UserStore {

    private userMap: Map<number, User> = new Map<number, User>()

    public async getById(id: number) {
        if (!this.userMap.has(id)) {
            const user = await getUserById(id)
            this.userMap.set(id, user)
        }

        return this.userMap.get(id) as User
    }

    public async getMe() {
        return await getMe()
    }
}

export default {
    userStore: new UserStore()
}
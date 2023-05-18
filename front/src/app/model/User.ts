export class User {
  id: number
  email: string
  password: string
  name: string
  surname: string
  role: string

  constructor(obj: any) {
    this.id = obj.id
    this.email = obj.email
    this.password = obj.password
    this.name = obj.name
    this.surname = obj.surname
    this.role = obj.role
  }

}

import bcrypt from 'bcryptjs'

hashPWD = function (args) {
  var salt = bcrypt.genSaltSync(10)
  var hash = bcrypt.hashSync(args, salt)
  return hash
}

comparePWD = function (clean, jumbled) {
  return bcrypt.compareSync(clean, jumbled)
}

export default {
  hashPWD,
  comparePWD
}

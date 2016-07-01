let chkUser = function (args, obj) {
  for (let args in obj) {
    if (!obj.hasOwnProperty(args)) {
      return false
    } else {
      return true
    }
  }
}

module.exports = chkUser

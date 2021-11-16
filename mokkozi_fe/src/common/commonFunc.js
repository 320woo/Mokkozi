// image 리스트를 받아 local URL을 생성합니다. (미리보기 기능 위해)
export function makeLocalURL(files) {
  console.log("commonFunc.makeLocalURL 3 : 함수 실행")
  let result = []

  for (let i=0; i < files.length; i++) {
    const previewURL = URL.createObjectURL(files[i])
    result.push(previewURL)
  }
  return result
}

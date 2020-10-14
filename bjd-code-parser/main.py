import json

def main():
    l = []
    ff = {

    }
    with open("법정동코드 전체자료.txt", "r", encoding='euc-kr') as reader:
        for line in reader:
            s = line.split()
            full_code = s[0]
            isOk = s[-1]
            rest = s[1:-1]
            full_name = rest
            rest = rest + ["" for i in range((4 - len(rest)))]
            시도_code = full_code[:2]
            시군구_code = full_code[2:5]
            읍면동_code = full_code[5:8]
            리_code = full_code[8:]
            시도_name = rest[0]
            시군구_name = rest[1]
            읍면동_name = rest[2]
            리_name = rest[3]
            ff[isOk] = isOk

            if isOk == "존재":
                isOk = True
            else:
                isOk = False
            print(시도_code, 시군구_code, 읍면동_code, 리_code, 시도_name, 시군구_name, 읍면동_name, 리_name, isOk)
            l += [{
                "시도_code": 시도_code,
                "시군구_code": 시군구_code,
                "읍면동_code": 읍면동_code,
                "리_code": 리_code,

                "시도_name": 시도_name,
                "시군구_name": 시군구_name,
                "읍면동_name": 읍면동_name,
                "리_name": 리_name,

                "존재여부": isOk,
                "full_code": full_code,
                "full_name": full_name

            }]
    l = l[1:]
    with open('target.json', 'w', encoding='utf-8')as f:
        json.dump(l, f, ensure_ascii=False)
    print(ff)
    # Press the green button in the gutter to run the script.


if __name__ == '__main__':
    main()

# See PyCharm help at https://www.jetbrains.com/help/pycharm/

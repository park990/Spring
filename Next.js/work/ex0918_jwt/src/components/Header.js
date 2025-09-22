"use client";
import TokenStore from "@/app/store/TokenStrore";
import Link from "next/link";

export default function Header() {
  //   const cookieStore = await cookies();
  //   const accesstoken = cookieStore.get("accessToken")?.value;
  // cookieStore.get("accessToken") 값이 있으면 accessToken 변수에 쿠키에 있는 토큰 값이 저장되지만
  // 없으면 undefined가 저장된다.
  const { accessToken, setToken } = TokenStore();

  function logout() {}

  const menuItem = [
    { title: "Home", path: "/" },
    { title: "Members", path: "/members" },
    { title: "Board", path: "/bbs" },
  ];

  const loginItem = [
    { title: "Login", path: "/members/login" },
    { title: "Signup", path: "/members/logout" },
    { title: "Signup", path: "/members/signup" },
  ];

  return (
    <div className="headWrap">
      <div className="left_bar">
        {menuItem.map(function (item, i) {
          return (
            <div key={i} className="left_item">
              <Link href={`${item.path}`}>{item.title}</Link>
            </div>
          );
        })}
      </div>

      <div className="right_bar">
        {accessToken != null ? (
          // 참(true)일 때: Logout 링크 하나만 보여줌
          <div className="right_item">
            <Link href="/members/logout">Logout</Link>
          </div>
        ) : (
          <>
            <div className="right_item">
              <Link href="/members/login">Login</Link>
            </div>
            <div className="right_item">
              <Link href="/members/signup">Signup</Link>
            </div>
          </>
        )}
      </div>
    </div>
  );
}

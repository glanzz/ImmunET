import Image from "next/image";
import Link from "next/link";
import { useState } from "react";
import { AiOutlinePlus } from "react-icons/ai";
import { MdOutlinePets, MdVaccines } from "react-icons/md";
import { IoMdLogOut } from "react-icons/io";
import { RxCross2 } from "react-icons/rx";
import { TbHome2 } from "react-icons/tb";
import logo from "../../../public/assets/logo.png";
import userIcon from "../../../public/assets/userIcon.png";

const Sidebar = ({ children, open, setOpen, nav, setNav }) => {
  const [toggle, setToggle] = useState({
    product: true,
    customer: true,
  });

  const toggleSection = (section) => {
    setToggle((prevState) => ({
      ...prevState,
      [section]: !prevState[section],
    }));
  };
  return (
    <div className=" bg-white sticky top-0 z-50">
      {/* for large device */}
      <div
        className={`${
          open ? "w-[70px]" : "w-[300px]"
        } duration-300 lg:block h-screen overflow-y-auto hidden example`}
      >
        {!open && (
          <div className=" ">
            <div className="ml-4">
              <Link href="/">
                <Image src={logo} alt="" className="" />
              </Link>
            </div>

            <div className="flex items-center space-x-1 mt-4 ml-4">
              <div>
                <Image width={50} src={userIcon} alt="" />
              </div>
              <div className="text-sm">
                <h1 className="font-semibold">Daniel Peters</h1>
                <h1>dpeters@csye6200.com</h1>
              </div>
            </div>
          </div>
        )}
        {!open && <hr className="w-full mt-3 mb-4" />}
        {/* links */}
        <div className={`${open ? "grid justify-center mr-2" : "bg-white grid ml-5"}`}>
          <Link
            href="/"
            className="p-3 flex items-center hover:bg-secondary hover:text-secondary rounded-l-full"
          >
            <TbHome2 className="mr-2" />

            {!open && <span className="ml-2">Dashboard</span>}
          </Link>
          <Link
            href="/add-doctor"
            className="p-3 flex items-center hover:bg-secondary hover:text-secondary rounded-l-full"
          >
            <AiOutlinePlus className="mr-2" />

            {!open && <span className="ml-2">Add Doctor</span>}
          </Link>
          <Link
            href="/addvaccine"
            className="p-3 flex items-center hover:bg-secondary hover:text-secondary rounded-l-full"
          >
            <MdVaccines className="mr-2" />
            {!open && <span className="ml-2">Create Vaccines</span>}
          </Link>
          <Link
            href="/vaccine-list"
            className="p-3 flex items-center hover:bg-secondary hover:text-secondary rounded-l-full"
          >
            <MdVaccines className="mr-2" />
            {!open && <span className="ml-2">Vaccines List</span>}
          </Link>

          <Link
            className="p-3 flex items-center hover:bg-secondary hover:text-secondary rounded-l-full"
            href="/pet-list"
          >
            <MdOutlinePets className="mr-2" />
            {!open && <span className="ml-2"> Pet List</span>}
          </Link>

          <Link
            className="p-3 flex items-center hover:bg-secondary hover:text-secondary rounded-l-full"
            href="#"
          >
            <IoMdLogOut className="mr-2" />
            {!open && <span className="ml-2">Log Out</span>}
          </Link>
        </div>
      </div>

      {/* for other devices */}
      <div className=" ">
        <div
          className={`${
            nav ? " w-[300px] left-0 duration-500" : " left-[-300px] duration-500 "
          } lg:hidden h-screen overflow-y-scroll bg-white absolute`}
        >
          <div className="">
            <div className="ml-4 my-5 flex justify-between">
              <Image src={logo} alt="" />
              <RxCross2 className="text-2xl cursor-pointer" onClick={() => setNav(!nav)} />
            </div>

            <div className="flex items-center space-x-1 mt-4 ml-4">
              <div>
                <Image width={50} src={userIcon} alt="" />
              </div>
              <div className="text-sm">
                <h1 className="font-semibold">admin user</h1>
                <h1>admin@gmail.com</h1>
              </div>
            </div>
          </div>
          <hr className="w-full mt-3 mb-4" />
          {/* links */}
          <div className="grid ml-4">
            <Link
              href="/"
              className="p-3 flex items-center hover:bg-secondary hover:text-secondary rounded-l-full"
            >
              <TbHome2 className="mr-2" />

              {!open && <span className="ml-2">Dashboard</span>}
            </Link>
            <Link
              href="/add-doctor"
              className="p-3 flex items-center hover:bg-secondary hover:text-secondary rounded-l-full"
            >
              <AiOutlinePlus className="mr-2" />
              {!open && <span className="ml-2">Add Doctor</span>}
            </Link>
            <Link
              href="/addvaccine"
              className="p-3 flex items-center hover:bg-secondary hover:text-secondary rounded-l-full"
            >
              <MdVaccines className="mr-2" />
              {!open && <span className="ml-2">Create Vaccines</span>}
            </Link>
            <Link
              href="/vaccine-list"
              className="p-3 flex items-center hover:bg-secondary hover:text-secondary rounded-l-full"
            >
              <MdVaccines className="mr-2" />
              {!open && <span className="ml-2"> Vaccines List</span>}
            </Link>

            <Link
              className="p-3 flex items-center hover:bg-secondary hover:text-secondary rounded-l-full"
              href="/pet-list"
            >
              <MdOutlinePets className="mr-2" />
              {!open && <span className="ml-2"> Pet List</span>}
            </Link>

            <Link
              className="p-3 flex items-center hover:bg-secondary hover:text-secondary rounded-l-full"
              href="#"
            >
              <IoMdLogOut className="mr-2" />
              {!open && <span className="ml-2"> Log Out</span>}
            </Link>
          </div>
        </div>
      </div>

      <main>{children}</main>
    </div>
  );
};

export default Sidebar;
